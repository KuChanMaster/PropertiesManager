package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Complaint;
import com.example.entity.Fixed;
import com.example.entity.Parking;
import com.example.service.ComplaintService;
import com.example.service.FixedService;
import com.example.service.ParkingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private ParkingService parkingService;

    @Resource
    private FixedService fixedService;

    @Resource
    private ComplaintService complaintService;

    @RequestMapping("/pie1")
    public Result getPi1() {

        List<Map<String, Object>> resultList = new ArrayList<>();
        //所有车位信息
        List<Parking> all = parkingService.selectAll(new Parking());
        //占用车位信息
        long occupyCount = all.stream().filter(x -> ObjectUtil.isNotEmpty(x.getUserId())).count();
        Map<String, Object> occupyMap = new HashMap<>();
        occupyMap.put("name", "占用");
        occupyMap.put("value", occupyCount);

        resultList.add(occupyMap);

        Map<String, Object> freeMap = new HashMap<>();
        freeMap.put("name", "限制");
        freeMap.put("value", all.size() - occupyCount);
        resultList.add(freeMap);
        return Result.success(resultList);
    }

    @RequestMapping("/pie2")
    public Result getPi2() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Complaint> all = complaintService.selectAll(new Complaint());
        //占用车位信息
        Map<String,Long> collect= all.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getStatus()))
                .collect(Collectors.groupingBy(Complaint::getStatus,Collectors.counting()));
        for (String key:collect.keySet()){
            Map<String,Object> map=new HashMap<>();
            map.put("name",key);
            map.put("value",collect.get(key));
            resultList.add(map);
        }
        return Result.success(resultList);
    }

    @RequestMapping("/pie3")
    public Result getPi3() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Fixed> all = fixedService.selectAll(new Fixed());
        //占用车位信息
        Map<String,Long> collect= all.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getStatus()))
                .collect(Collectors.groupingBy(Fixed::getStatus,Collectors.counting()));
        for (String key:collect.keySet()){
            Map<String,Object> map=new HashMap<>();
            map.put("name",key);
            map.put("value",collect.get(key));
            resultList.add(map);
        }
        return Result.success(resultList);
    }

}
