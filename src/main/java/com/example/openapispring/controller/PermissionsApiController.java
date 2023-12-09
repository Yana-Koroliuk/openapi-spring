package com.example.openapispring.controller;

import dto.PermissionDTO;
import controller.PermissionsApi;
import com.example.openapispring.entity.Permission;
import com.example.openapispring.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${openapi.projectManagement.base-path:/api}")
public class PermissionsApiController implements PermissionsApi {

    private final PermissionService permissionService;

    @Autowired
    public PermissionsApiController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public ResponseEntity<List<dto.Permission>> permissionsGet() {
        List<Permission> permissions = permissionService.getAllPermission();
        List<dto.Permission> permissiondtos = permissions.stream()
                .map(permission -> {
                    dto.Permission permissiondto = new dto.Permission();
                    permissiondto.setId(permission.getId());
                    permissiondto.setAction(permission.getAction());
                    return permissiondto;
                })
                .toList();
        return ResponseEntity.ok(permissiondtos);
    }

    @Override
    public ResponseEntity<Void> permissionsPermissionIdDelete(Integer permissionId) {
        permissionService.deletePermission(permissionId.longValue());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<dto.Permission> permissionsPermissionIdGet(Integer permissionId) {
        Permission permission = permissionService.getPermissionById(permissionId.longValue());
        dto.Permission permissiondto = new dto.Permission();
        permissiondto.setId(permission.getId());
        permissiondto.setAction(permission.getAction());
        return ResponseEntity.ok(permissiondto);
    }

    @Override
    public ResponseEntity<dto.Permission> permissionsPermissionIdPut(Integer permissionId, @Valid PermissionDTO permissionDTO) {
        Permission updatedPermission = permissionService.updatePermission(permissionId.longValue(), permissionDTO);
        dto.Permission updatedPermissiondto = new dto.Permission();
        updatedPermissiondto.setId(updatedPermission.getId());
        updatedPermissiondto.setAction(updatedPermission.getAction());
        return ResponseEntity.ok(updatedPermissiondto);
    }

    @Override
    public ResponseEntity<dto.Permission> permissionsPost(@Valid PermissionDTO permissionDTO) {
        Permission createdPermission = permissionService.createPermission(permissionDTO);
        dto.Permission createdPermissiondto = new dto.Permission();
        createdPermissiondto.setId(createdPermission.getId());
        createdPermissiondto.setAction(createdPermission.getAction());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPermissiondto);
    }
}

