package varusmiesweb.controller;

import VarusmiesDomain.Palveluspaikka;
import VarusmiesDomain.Varusmies;
import VarusmiesDomain.Varusmiesrekisteri;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import varusmiesweb.papu.RekisteriPapu;

/**
 * Tämä luokka toimii kontrollerina varusmiesten yleisnäkymään tuleville pyynnöille
 * @author Aleksanteri
 * @version 1.0
 */
@Controller
@RequestMapping(value="/palveluspaikka/{id}")
public class VarusmiehetController {
    
    private static Varusmiesrekisteri rekisteri = RekisteriPapu.annaRekisteri();
    
    /**
     * Päämetodi, joka palauttaa aina get-pyynnön kyseisen palveluspaikan kohdalta
     * @param id palveluspaikan id, jonka varusmiehet halutaan hakea
     * @param model model johon lisätään varusmiehet
     * @return varusmiesten listausnäkymä
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getVarusmiehet(@PathVariable int id, Model model) {
        // haetaan palveluspaikka id:n mukaan
        List<Palveluspaikka> ppaikat = rekisteri.haePalveluspaikat();
        Palveluspaikka laitettava = null;
        for(Palveluspaikka p : ppaikat) {
            if(p.getPalvpaikID() == id) {
                laitettava = p;
                break;
            }
        }
        if(laitettava == null) {
            return "redirect:/palveluspaikat";
        }
        model.addAttribute("palveluspaikka", laitettava);
        model.addAttribute("varusmiehet", rekisteri.haeVarusmiehet(id));
        return "/WEB-INF/views/varusmiehet.jsp";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String lisaaVarusmies(@PathVariable int id,
            @RequestParam(required=true) String sukunimi,
            @RequestParam(required=true) String etunimet,
            @RequestParam(required=true) String hetu,
            @RequestParam(required=true) String osoite,
            @RequestParam(required=true) String paikkakunta,
            @RequestParam(required=true) String postinro,
            @RequestParam(required=false, defaultValue = "") String cooper,
            @RequestParam(required=true) String palvpaikid) { // RequestParamilla tiedot kentistä
        if(!sukunimi.isEmpty()
                && !etunimet.isEmpty()
                && !hetu.isEmpty()
                && !osoite.isEmpty()
                && !paikkakunta.isEmpty()
                && !postinro.isEmpty() // ei cooperia
                && !palvpaikid.isEmpty()) {
            Varusmies vmies = new Varusmies(true);
            vmies.aseta(0, "0");
            vmies.aseta(1,sukunimi);
            vmies.aseta(2,etunimet);
            vmies.aseta(3,hetu);
            vmies.aseta(4,osoite);
            vmies.aseta(5,paikkakunta);
            vmies.aseta(6,postinro);
            vmies.aseta(7,cooper);
            vmies.aseta(8,palvpaikid);
            rekisteri.lisaaVarusmies(vmies);
            rekisteri.tallenna();
        }
        return "redirect:/palveluspaikka/" + id;
    }
    
    @RequestMapping(value="/{vid}/delete",method = RequestMethod.POST)
    public String poistaVarusmies(@PathVariable int id, @PathVariable int vid) {
        List<Varusmies> varusmiehet = rekisteri.haeVarusmiehet(id);
        for(Varusmies v : varusmiehet) {
            if(v.getVid() == vid) {
                rekisteri.poista(v);
                break;
            }
        }
        return "redirect:/palveluspaikka/" + id;
    }
    
}
