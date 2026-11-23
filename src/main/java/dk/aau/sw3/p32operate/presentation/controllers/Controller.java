package dk.aau.sw3.p32operate.presentation.controllers;

import dk.aau.sw3.p32operate.business.services.interfaces.IConsumptionService;
import dk.aau.sw3.p32operate.dataAccess.repositories.IRoomRepository;
import dk.aau.sw3.p32operate.presentation.mappers.ConsumptionDTOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Controller {
    private final IConsumptionService consumptionService;
    private final ConsumptionDTOMapper DTOMapper;
    private final IRoomRepository roomRepository;

    public Controller(IConsumptionService service, ConsumptionDTOMapper mapper, IRoomRepository repository){
        this.consumptionService = service;
        this.DTOMapper = mapper;
        this.roomRepository = repository;
    }

    @GetMapping("/2operate")
    public void message() {
        System.out.println(DTOMapper.mapToDTOList(consumptionService.getAll()));
        System.out.println(roomRepository.findAll().stream().findFirst().get().getRoomRecordings().stream().findFirst().get().getHumidity());
        System.out.println(roomRepository.findByRoomId(1).get().getRoomName());
    }
}
