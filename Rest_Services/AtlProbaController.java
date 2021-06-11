package atl.service.rest;

import atl.model.Proba;
import atl.repo.RepositoryProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/atl/probe")
public class AtlProbaController {

    @Autowired
    private RepositoryProbe probeRepository;

    @RequestMapping( method = RequestMethod.GET)
    public Proba[] getAll(){
        List<Proba> probe = StreamSupport.stream(probeRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return probe.toArray(new Proba[probe.size()]);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String id){
        List<Proba> probe = new ArrayList<>();
        Long id_long = Long.parseLong(id);
        Proba proba = probeRepository.findOne(id_long);
        probe.add(proba);
        if (proba == null)
            return new ResponseEntity<String>("Proba nu a fost gasita!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Proba[]>(probe.toArray(new Proba[probe.size()]), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Proba create(@RequestBody Proba proba){
        probeRepository.save(proba);
        return proba;
    }

    @RequestMapping(value = "/descriere/{descriere}", method = RequestMethod.GET)
    public ResponseEntity<?> getByDescriere(@PathVariable String descriere){
        List<Proba> probe = new ArrayList<>();
        Proba proba = probeRepository.findProbaByDescriere(descriere);
        probe.add(proba);
        if (proba == null)
            return new ResponseEntity<String>("Proba nu a fost gasita!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Proba[]>(probe.toArray(new Proba[probe.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        int status = probeRepository.deleteProba(id);
        if(status == 1){
        return new ResponseEntity<String>("proba a fost stearsa!", HttpStatus.OK);}
        else{
            return new ResponseEntity<String>("Proba nu a fost gasita!", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Proba proba){
        int status = probeRepository.updateProba(id, proba);
        if(status == 1){
            return new ResponseEntity<String>("proba a fost modificata!", HttpStatus.OK);}
        else{
            return new ResponseEntity<String>("Proba nu a fost gasita!", HttpStatus.NOT_FOUND);
        }
    }
}
