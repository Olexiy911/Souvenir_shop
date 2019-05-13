package souvenir_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping()
public class PurchaseController {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @PostMapping(path = "/purchases")
    public StringBuilder newPurchases(@Valid @RequestBody Purchase purchase) throws IOException {
        Double basecurrency = currencyRepository.findConversionRate("EUR", purchase.getCurrency(), purchase.getDate());
        Double baseamount = purchase.getAmount() / basecurrency;

        purchase.setBasecarrency("EUR");
        purchase.setBaseamount(baseamount);

        purchaseRepository.save(purchase);

        String d = new SimpleDateFormat("yyyy-MM-dd").format(purchase.getDate());
        double totalPrise = (double) Math.round(purchase.getAmount() * 100.0) / 100.0;

        return new StringBuilder().append(d).append(" ").append(purchase.getProductName()).append(" ")
                .append(totalPrise).append(" ").append(purchase.getCurrency());
    }

    @GetMapping(path = "/purchases")
    public @ResponseBody
    Iterable<Respons> getAllPurchases() {
        return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false).map((purchase -> {
            Respons respons = new Respons();
            respons.setAmount(purchase.getAmount());
            respons.setCurrency(purchase.getCurrency());
            respons.setDate(purchase.getDate());
            respons.setId(purchase.getId());
            respons.setProductName(purchase.getProductName());

            return respons;
        })).collect(Collectors.toList());

    }


    @DeleteMapping(path = "/purchases/{date}")
    public String deleteIdPurchase(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           Date date) {

        purchaseRepository.deleteAll(purchaseRepository.findAllByDate(date));
        return "Deleted " + date;

    }

    @GetMapping(path = "/purchases/{date}/report")
    public String getPurchase(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                              @RequestParam(value = "currency", required = false) String currency) throws IOException {
        List<Purchase> allByDate = purchaseRepository.findAllByDate(date);
        Double total = null;
        for (Purchase p : allByDate) {
            if (p.getBaseamount() != null) {
                total = +p.getBaseamount();
            }
        }

        Double basecurrency = currencyRepository.findConversionRate("EUR", currency, date);
        Double result = total / basecurrency;
        double totalPrise = (double) Math.round(result * 100.0) / 100.0;

        return totalPrise + " " + currency;
    }

}
