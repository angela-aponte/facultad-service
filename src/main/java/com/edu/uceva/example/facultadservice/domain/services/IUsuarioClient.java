package com.edu.uceva.example.facultadservice.domain.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "usuarioservice", url = "http://api.mewings.joptionpane.software")
public interface IUsuarioClient {
    @GetMapping("api/v1/usuario-service/usuarios")
    ResponseEntity<Map<String, Object>> getUsuarios();
}
