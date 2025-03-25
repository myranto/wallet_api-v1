package mg.projects.wallet.common;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.format.ToJsonData;

public class CommonController<S extends CommonService,T extends BaseEntity>  {
    private final S service;
    public CommonController(S service) {
        this.service = service;
    }

    public S getService() {
        return service;
    }
    @PostMapping("")
    public ResponseEntity<?> saveModel(@RequestBody T model) {
        try {
            return ResponseEntity.ok(new ToJsonData<>(service.save(model), null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateModel(@RequestBody T model) {
        try {
            service.save(model);
            return ResponseEntity.ok(new ToJsonData<>("Modification réussi", null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            service.deleteById(Integer.parseInt(id));
            return ResponseEntity.ok(new ToJsonData<>("Suppression réussi", null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, "Suppression impossible"), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> findAllModel(@RequestParam(value = "key", required = false) String key) {
        try {
            List<T> list = service.findAll(key);

            return ResponseEntity.ok(new ToJsonData<>(list, null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<?> findAllpaginateModel(@PathVariable("page") int page, @PathVariable("size") int size, @RequestParam(value = "key", required = false) String key) {
        try {
            return ResponseEntity.ok(new ToJsonData<>(service.getPaginated(page, size, key), null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/count")
    public ResponseEntity<?> countAllModel() {
        try {
            return ResponseEntity.ok(new ToJsonData<>(getService().getJpa().count(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
