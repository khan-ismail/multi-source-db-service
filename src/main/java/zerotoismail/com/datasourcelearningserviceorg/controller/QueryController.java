package zerotoismail.com.datasourcelearningserviceorg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;
import zerotoismail.com.datasourcelearningserviceorg.model.MySQLConnectionDetails;
import zerotoismail.com.datasourcelearningserviceorg.security.JwtProvider;
import zerotoismail.com.datasourcelearningserviceorg.service.ConnectionConfigService;
import zerotoismail.com.datasourcelearningserviceorg.service.MyService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QueryController {

    private final JwtProvider jwtProvider;
    private ConnectionConfigService tenantQueryService;
    private MyService myService;

    public QueryController(ConnectionConfigService tenantQueryService, MyService myService, JwtProvider jwtProvider) {
        this.tenantQueryService = tenantQueryService;
        this.myService = myService;
        this.jwtProvider = jwtProvider;
    }

    @RequestMapping("/execute/{id}")
    public ResponseEntity<?> query(@PathVariable Long id) {

        try {
            MySQLConnectionDetails connection = tenantQueryService.getTenantConnection(id);
            return ResponseEntity.ok(connection);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping("/run/{id}")
    public ResponseEntity<?> runQuery(@PathVariable Long id) {

        try {
            List<User> result = myService.executeQueryForTenant(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String tenantId, @RequestParam String username) {
        return jwtProvider.generateJwtToken(tenantId);
    }
}
