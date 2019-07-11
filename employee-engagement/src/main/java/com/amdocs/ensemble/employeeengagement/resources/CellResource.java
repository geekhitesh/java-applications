package com.amdocs.ensemble.employeeengagement.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.ensemble.employeeengagement.entities.Cell;
import com.amdocs.ensemble.employeeengagement.entities.CellFilter;
import com.amdocs.ensemble.employeeengagement.repositories.CellFilterRepository;
import com.amdocs.ensemble.employeeengagement.repositories.CellRepository;
import com.mysql.cj.Session;

@RestController
public class CellResource {
	
	@Autowired
	private CellRepository cellRepository;
	
	@Autowired
	private CellFilterRepository cellFilterRepository;
	
	@GetMapping("/cells")
	@CrossOrigin
	public List<Cell> getCells() {
		
		List<Cell> cells = cellRepository.findAll();
		
		return cells;
	}
	
	
	
	@PostMapping("create-cell")
	@CrossOrigin
	public void save(@Valid @RequestBody Cell cell) {
		System.out.println(cell);
		
		
		cellRepository.save(cell);
		
		List<CellFilter>filters = cell.getFilters();
//		filters.stream().map(filter -> filter.setCell(cell));
		
		for(CellFilter filter: filters) {
			filter.setCell(cell);
			cellFilterRepository.save(filter);
		}
		
		
		                 
		
	}

}
