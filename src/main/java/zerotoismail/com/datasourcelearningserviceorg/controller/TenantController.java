package zerotoismail.com.datasourcelearningserviceorg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerotoismail.com.datasourcelearningserviceorg.service.TenantService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("")
    public ResponseEntity<?> getTenantlist(){
        return ResponseEntity.ok(Map.of("data",tenantService.getAllTenantIds()));
    }
}
