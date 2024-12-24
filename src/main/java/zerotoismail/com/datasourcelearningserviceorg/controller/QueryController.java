package zerotoismail.com.datasourcelearningserviceorg.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerotoismail.com.datasourcelearningserviceorg.dto.QueryRequestDto;
import zerotoismail.com.datasourcelearningserviceorg.dto.ResponseDto;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;
import zerotoismail.com.datasourcelearningserviceorg.security.JwtProvider;
import zerotoismail.com.datasourcelearningserviceorg.service.QueryBuilderService;
import zerotoismail.com.datasourcelearningserviceorg.utils.QueryUtils;

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
        return ResponseEntity.ok(result);
    }
}
