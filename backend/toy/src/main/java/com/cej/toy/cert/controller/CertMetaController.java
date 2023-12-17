package com.cej.toy.cert.controller;

import com.cej.toy.cert.domain.CertDto;
import com.cej.toy.cert.service.CertService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/cert")
public class CertMetaController {

    private final CertService certService;

    @GetMapping(value = {"/all", "/all/{pageNo}"})
    public ResponseEntity<List<CertDto>> getAllCertList(@PathVariable Optional<Integer> pageNo){
        return new ResponseEntity<>(certService.getAllCertList(pageNo), HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<CertDto>> getCertList(@PathVariable String category){
        return new ResponseEntity<>(certService.getCertList(category), HttpStatus.OK);
    }
}
