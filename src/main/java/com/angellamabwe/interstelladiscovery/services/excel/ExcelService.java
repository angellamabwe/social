package com.angellamabwe.interstelladiscovery.services.excel;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angellamabwe.interstelladiscovery.entities.Planet;
import com.angellamabwe.interstelladiscovery.entities.Route;
import com.angellamabwe.interstelladiscovery.repositories.SavePlanetstoDB;
import com.angellamabwe.interstelladiscovery.repositories.SaveRoutestoDB;

@Service
public class ExcelService {

	@Autowired
	private SavePlanetstoDB planetsRepository;

	@Autowired
	private SaveRoutestoDB routesRepository;

	public void ReadDataFromExcel(String excelPath)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		Workbook workbook = WorkbookFactory.create(new File(excelPath));

		for (Sheet sheet : workbook) {
			String sheetName = sheet.getSheetName();
//			DataFormatter formatter = new DataFormatter();
			if (sheetName.equalsIgnoreCase("Planet Names")) {

				for (Row row : sheet) {
					String node = row.getCell(0).getStringCellValue();
					String name = row.getCell(1).getStringCellValue();

					Planet planet = new Planet();
					planet.setNode(node);
					planet.setName(name);
					planetsRepository.save(planet);
				}
			}

			if (sheetName.equalsIgnoreCase("Routes")) {
				for (Row row : sheet) {
//					int id = (int) row.getCell(0).getNumericCellValue();
//					int id = Integer.parseInt(row.getCell(0).getStringCellValue());
//					throwing NumberFormatException
//					Cell cell = row.getCell(0);
//					String cellValue = formatter.formatCellValue(cell);
//					int id = Integer.parseInt(cellValue);
					String pOrigin = row.getCell(1).getStringCellValue();
					String pDestination = row.getCell(2).getStringCellValue();
					double distance = row.getCell(3).getNumericCellValue();
					double distanceAfterDelay = row.getCell(4).getNumericCellValue();

					Route route = new Route();
//					route.setRouteId(id);
					route.setPlanetOrigin(pOrigin);
					route.setPlanetDest(pDestination);
					route.setDistance(distance);
					route.setDistanceAfterDelay(distanceAfterDelay);
					routesRepository.save(route);

				}
			}

//			if (sheetName.equalsIgnoreCase("Traffic")) {
//				for (Row row : sheet) {
//					int id = (int) row.getCell(0).getNumericCellValue();
//					String pOrigin = row.getCell(1).getStringCellValue();
//					String pDestination = row.getCell(2).getStringCellValue();
//					double delay = row.getCell(3).getNumericCellValue();
//					
//					TrafficDelay trafficDelay = new TrafficDelay();
////					trafficDelay.setRouteId(id);
//					trafficDelay.setPlanetOrigin(pOrigin);
//					trafficDelay.setPlanetDest(pDestination);
//					trafficDelay.setDelay(delay);
//					delayRepository.save(trafficDelay);
//					
//				}
//			}

		}
	}

//	retrieves Planets Node & their names
	public Iterable<Planet> retrieveNodes() {
		Iterable<Planet> allPlanets = planetsRepository.findAll();
		return allPlanets;
	}

	public Iterable<Route> retrieveRoutes() {
		Iterable<Route> allRoutes = routesRepository.findAll();
		return allRoutes;
	}

}
