package ru.vsu.personalWallet.controller;

import org.springframework.web.bind.annotation.*;
//import ru.vsu.personalWallet.HttpResponse.HttpResponse;
//import ru.vsu.personalWallet.domain.OperationType;
//import ru.vsu.personalWallet.domain.dto.AimDto;
//import ru.vsu.personalWallet.service.AimService;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;
@RequestMapping("/aims")
@RestController
public class AimController {


    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestHeader(USER_ID_HEADER) String userId) {
        return userId;
       // return true;//aimService.findAllByUserId(userId);
    }



    //private AimService aimService;

//    @Autowired
//    AimController(AimService aimService) {
//        this.aimService = aimService;
//    }

//    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
//    public boolean delete(String id) {
//        return aimService.delete(id);
//    }

//    @RequestMapping(value = "add", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public boolean add(@RequestBody AimDto aimDto) {
//        return aimService.add(aimDto);
//    }
//
//    @RequestMapping(value = "edit", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public boolean edit(@RequestBody AimDto aimDto) {
//        return aimService.edit(aimDto);
//    }
//
//    private ResponseEntity getAimDtoOrCode404(String id) {
//        if (aimService.findById(id) == null) {
//            HttpHeaders httpHeader = new HttpHeaders();
//            httpHeader.setConnection("close");
//            return new ResponseEntity<>(
//                    new HttpResponse()
//                            .setTimestamp(Instant.now().getEpochSecond())
//                            .setStatus(404)
//                            .setError("Not found")
//                            .setMessage("Aim with id=" + id + " not found")
//                            .setPath("/aims"),
//                    httpHeader,
//                    HttpStatus.NOT_FOUND);
//        } else return new ResponseEntity<>(aimService.findById(id), HttpStatus.OK);
//    }
//
//
//    @RequestMapping(method = RequestMethod.GET, params = {"id"})
//    public ResponseEntity getById(String id) {
//        return getAimDtoOrCode404(id);
//    }
//
//    @RequestMapping(value = "edit", method = RequestMethod.GET)
//    public ResponseEntity edit(String id) {
//        return getAimDtoOrCode404(id);
//    }
//
//
//    @RequestMapping(method = RequestMethod.GET, params = {"userId"})
//    public List<AimDto> getAll(String userId) {
//        return aimService.findAllByUserId(userId);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = {"name"})
//    public List<AimDto> getByName(String name) {
//        return aimService.findByName(name);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = {"operationType"})
//    public List<AimDto> getByOperationType(OperationType operationType) {
//        return aimService.findByOperationType(operationType);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = {"creationDate"})
//    public List<AimDto> getByDate(Timestamp date) {
//        return aimService.findByCreationDate(date);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = {"period"})
//    public List<AimDto> getByPeriod(Timestamp period) {
//        return aimService.findByPeriod(period);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = {"moneyValue"})
//    public List<AimDto> getByMoneyValue(long moneyValue) {
//        return aimService.findByMoneyValue(moneyValue);
//    }
}
