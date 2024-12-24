package zerotoismail.com.datasourcelearningserviceorg.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerotoismail.com.datasourcelearningserviceorg.dto.QueryRequestDto;
import zerotoismail.com.datasourcelearningserviceorg.dto.ResponseDto;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;
import zerotoismail.com.datasourcelearningserviceorg.multiTenancy.model.CurrentState;
import zerotoismail.com.datasourcelearningserviceorg.security.JwtProvider;
import zerotoismail.com.datasourcelearningserviceorg.service.QueryBuilderService;
import zerotoismail.com.datasourcelearningserviceorg.utils.QueryUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/queries")
public class QueryController {

    private final JwtProvider jwtProvider;
    private QueryBuilderService queryBuilderService;

    public QueryController(QueryBuilderService queryBuilderService, JwtProvider jwtProvider) {
        this.queryBuilderService = queryBuilderService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/run")
    public ResponseEntity<?> runQuery(@RequestBody QueryRequestDto queryDto) {
        String query = queryDto.getQuery();

        if (!QueryUtils.validateQuery(query)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.toString(), "Only SELECT queries are allowed."));
        }

        List<Map<String, Object>> result = queryBuilderService.executeQueryForTenant(query);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/state")
    public ResponseEntity<?> getCurrentState(@RequestHeader("Authorization") String authorizationHeader,
                                             @RequestParam(required = false)
                                             String paramTenantId) {

        String tokenFromAuth = jwtProvider.getTenantIdFromJwt(authorizationHeader);
        String tenantId = tokenFromAuth != null ? tokenFromAuth : paramTenantId;
        Object currentState = queryBuilderService.getConfiguration(tenantId);

        return new ResponseEntity<>(Map.of("current_state", currentState), HttpStatus.OK);
    }


    @PutMapping("/state")
    public ResponseEntity<?> updateCurrentState(@RequestHeader("Authorization") String authorizationHeader,
                                                @RequestBody CurrentState currentState,
                                                @RequestParam(required = false) String paramTenantId) {

        String tokenFromAuth = jwtProvider.getTenantIdFromJwt(authorizationHeader);
        String tenantId = tokenFromAuth != null ? tokenFromAuth : paramTenantId;

        if (tenantId == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.toString(), "tenantId is required"));
        }

        currentState.setTenantId(tenantId);
        CurrentState currentState1 = queryBuilderService.saveCurrentState(currentState);

        return new ResponseEntity<>(Map.of("current_state", currentState), HttpStatus.OK);
    }
}
