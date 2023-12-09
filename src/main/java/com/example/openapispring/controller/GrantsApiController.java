package com.example.openapispring.controller;

import dto.GrantDTO;
import controller.GrantsApi;
import com.example.openapispring.entity.Grant;
import com.example.openapispring.service.GrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${openapi.projectManagement.base-path:/api}")
public class GrantsApiController implements GrantsApi {

    private final GrantService grantService;

    @Autowired
    public GrantsApiController(GrantService grantService) {
        this.grantService = grantService;
    }

    @Override
    public ResponseEntity<List<dto.Grant>> grantsByPermissionPermissionIdGet(Integer permissionId) {
        List<Grant> grants = grantService.getGrantsByPermissionId(permissionId.longValue());
        List<dto.Grant> grantdtos = grants.stream()
                .map(grant -> {
                    dto.Grant grantdto = new dto.Grant();
                    grantdto.setId(grant.getId());
                    grantdto.setRoleId(grant.getRole().getId());
                    grantdto.setPermissionId(grant.getPermission().getId());
                    grantdto.setName(grant.getRole().getName());
                    grantdto.setDescription(grant.getRole().getDescription());
                    grantdto.setAction(grant.getPermission().getAction());
                    return grantdto;
                })
                .toList();
        return ResponseEntity.ok(grantdtos);
    }

    @Override
    public ResponseEntity<List<dto.Grant>> grantsByRoleRoleIdGet(Integer roleId) {
        List<Grant> grants = grantService.getGrantsByRoleId(roleId.longValue());
        List<dto.Grant> grantdtos = grants.stream()
                .map(grant -> {
                    dto.Grant grantdto = new dto.Grant();
                    grantdto.setId(grant.getId());
                    grantdto.setRoleId(grant.getRole().getId());
                    grantdto.setPermissionId(grant.getPermission().getId());
                    grantdto.setName(grant.getRole().getName());
                    grantdto.setDescription(grant.getRole().getDescription());
                    grantdto.setAction(grant.getPermission().getAction());
                    return grantdto;
                })
                .toList();
        return ResponseEntity.ok(grantdtos);
    }

    @Override
    public ResponseEntity<List<dto.Grant>> grantsGet() {
        List<Grant> grants = grantService.getAllGrants();
        List<dto.Grant> grantdtos = grants.stream()
                .map(grant -> {
                    dto.Grant grantdto = new dto.Grant();
                    grantdto.setId(grant.getId());
                    grantdto.setRoleId(grant.getRole().getId());
                    grantdto.setPermissionId(grant.getPermission().getId());
                    grantdto.setName(grant.getRole().getName());
                    grantdto.setDescription(grant.getRole().getDescription());
                    grantdto.setAction(grant.getPermission().getAction());
                    return grantdto;
                })
                .toList();
        return ResponseEntity.ok(grantdtos);
    }

    @Override
    public ResponseEntity<Void> grantsGrantIdDelete(Integer grantId) {
        grantService.deleteGrant(grantId.longValue());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<dto.Grant> grantsGrantIdGet(Integer grantId) {
        Grant grant = grantService.getGrantById(grantId.longValue());
        dto.Grant grantdto = new dto.Grant();
        grantdto.setId(grant.getId());
        grantdto.setRoleId(grant.getRole().getId());
        grantdto.setPermissionId(grant.getPermission().getId());
        grantdto.setName(grant.getRole().getName());
        grantdto.setDescription(grant.getRole().getDescription());
        grantdto.setAction(grant.getPermission().getAction());
        return ResponseEntity.ok(grantdto);
    }

    @Override
    public ResponseEntity<dto.Grant> grantsGrantIdPut(Integer grantId, @Valid GrantDTO grantDTO) {
        Grant updatedGrant = grantService.updateGrant(grantId.longValue(), grantDTO);
        dto.Grant grantdto = new dto.Grant();
        grantdto.setId(updatedGrant.getId());
        grantdto.setRoleId(updatedGrant.getRole().getId());
        grantdto.setPermissionId(updatedGrant.getPermission().getId());
        grantdto.setName(updatedGrant.getRole().getName());
        grantdto.setDescription(updatedGrant.getRole().getDescription());
        grantdto.setAction(updatedGrant.getPermission().getAction());
        return ResponseEntity.ok(grantdto);
    }

    @Override
    public ResponseEntity<dto.Grant> grantsPost(@Valid GrantDTO grantDTO) {
        Grant createdGrant = grantService.createGrant(grantDTO);
        dto.Grant grantdto = new dto.Grant();
        grantdto.setId(createdGrant.getId());
        grantdto.setRoleId(createdGrant.getRole().getId());
        grantdto.setPermissionId(createdGrant.getPermission().getId());
        grantdto.setName(createdGrant.getRole().getName());
        grantdto.setDescription(createdGrant.getRole().getDescription());
        grantdto.setAction(createdGrant.getPermission().getAction());
        return ResponseEntity.status(HttpStatus.CREATED).body(grantdto);
    }
}
