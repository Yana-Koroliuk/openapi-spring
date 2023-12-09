package com.example.openapispring.controller;

import dto.RoleDTO;
import controller.RolesApi;
import com.example.openapispring.entity.Role;
import com.example.openapispring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${openapi.projectManagement.base-path:/api}")
public class RolesApiController implements RolesApi {

    private final RoleService roleService;

    @Autowired
    public RolesApiController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<List<dto.Role>> rolesGet() {
        List<Role> roles = roleService.getAllRoles();
        List<dto.Role> roledtos = roles.stream()
                .map(role -> {
                    dto.Role roledto = new dto.Role();
                    roledto.setId(role.getId());
                    roledto.setName(role.getName());
                    roledto.setDescription(role.getDescription());
                    return roledto;
                 })
                .toList();
        return ResponseEntity.ok(roledtos);
    }

    @Override
    public ResponseEntity<dto.Role> rolesPost(@Valid RoleDTO roleDTO) {
        Role createdRole = roleService.createRole(roleDTO);
        dto.Role createdRoledto = new dto.Role();
        createdRoledto.setId(createdRole.getId());
        createdRoledto.setName(createdRole.getName());
        createdRoledto.setDescription(createdRole.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoledto);
    }

    @Override
    public ResponseEntity<Void> rolesRoleIdDelete(Integer roleId) {
        roleService.deleteRole(roleId.longValue());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<dto.Role> rolesRoleIdGet(Integer roleId) {
        Role role = roleService.getRoleById(roleId.longValue());
        dto.Role roledto = new dto.Role();
        roledto.setId(role.getId());
        roledto.setName(role.getName());
        roledto.setDescription(role.getDescription());
        return ResponseEntity.ok(roledto);
    }

    @Override
    public ResponseEntity<dto.Role> rolesRoleIdPut(Integer roleId, @Valid RoleDTO roleDTO) {
        Role updatedRole = roleService.updateRole(roleId.longValue(), roleDTO);
        dto.Role updatedRoledto = new dto.Role();
        updatedRoledto.setId(updatedRole.getId());
        updatedRoledto.setName(updatedRole.getName());
        updatedRoledto.setDescription(updatedRole.getDescription());
        return ResponseEntity.ok(updatedRoledto);
    }
}

