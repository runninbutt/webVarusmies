/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varusmiesweb.controller;

import VarusmiesDomain.Palveluspaikka;
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
 * Tämä käsittelee palveluspaikat -näkymän pyynnöt
 * @author Aleksanteri
 * @version 1.0
 */
@Controller
public class PalveluspaikatController {
    
    private static Varusmiesrekisteri rekisteri = RekisteriPapu.annaRekisteri();
    
    @RequestMapping(method = RequestMethod.GET)
    public String defaultGet() {
        return "redirect:/palveluspaikat";
    }
    
    @RequestMapping(value="/palveluspaikat",method=RequestMethod.GET)
    public String getPalveluspaikat(Model model) {
        List<Palveluspaikka> palveluspaikat = rekisteri.haePalveluspaikat();
        model.addAttribute("palveluspaikat", palveluspaikat);
        return "/WEB-INF/views/palveluspaikat.jsp";
    }
    
    @RequestMapping(value="/palveluspaikat",method = RequestMethod.POST)
    public String postPalveluspaikka(@RequestParam(required=true) String nimi, @RequestParam(required=true) String osoite, @RequestParam(required=true) String postinro, @RequestParam(required=true) String paikkakunta) {
        if(!nimi.isEmpty() && !osoite.isEmpty() && !postinro.isEmpty() && !paikkakunta.isEmpty()) {
            Palveluspaikka uusi = new Palveluspaikka(nimi, osoite, Integer.parseInt(postinro), paikkakunta);
            rekisteri.lisaaPalveluspaikka(uusi);
        }
        return "redirect:/palveluspaikat";
    }
    
    @RequestMapping(value="/palveluspaikat/{id}/delete")
    public String post(@PathVariable int id) {
        // TODO: pitää poistaa palveluspaikka
        List<Palveluspaikka> palveluspaikat = rekisteri.haePalveluspaikat();
        for(Palveluspaikka p : palveluspaikat) {
            if(p.getPalvpaikID() == id) {
                rekisteri.poistaPalveluspaikka(p);
                break;
            }
        }
        return "redirect:/palveluspaikat";
    }
}
