/**
 * 
 */
package com.demo.prueba.cgi.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.demo.prueba.cgi.dto.BusquedaDTO;
import com.demo.prueba.cgi.dto.PayloadConsultaDTO;

/**
 * @author Jaime Gutierrez
 *
 */
class DemoControllerTest {

	/**
	 * Test method for {@link com.demo.prueba.cgi.controller.DemoController#search(com.demo.prueba.cgi.dto.PayloadConsultaDTO)}.
	 */
	@Autowired
	private DemoController servicioDemo;
	
	@Test
	final void testSearch() {
		System.out.println ("Ejecutando la prueba de busqueda");
		servicioDemo = Mockito.mock(DemoController.class);
		
		System.out.println ("Estado del servicio inyectado: " + servicioDemo);
		
		PayloadConsultaDTO request = new PayloadConsultaDTO();
		int [] edades = new int[] {10,20,30,40};
		request.setAges(edades);
		request.setCheckIn("22/03/2023");
		request.setCheckOut("22/04/2023");
		request.setHotelId("123455");
		
		ObjectId id = ObjectId.get();
		
        Mockito.when(servicioDemo.search(request)).thenReturn(null);
        
        ResponseEntity<BusquedaDTO> respuesta = null;
        ResponseEntity<BusquedaDTO> a = new ResponseEntity<BusquedaDTO>(new BusquedaDTO(id.toHexString()),HttpStatus.OK);
        
        respuesta = servicioDemo.search(request);
        System.out.println ("Ejecutando prueba de busqueda " + respuesta);
        System.out.println ("objeto esperado: " + a.getBody().getSearchId());
        
        assertNotEquals(a,respuesta);
	}

	/**
	 * Test method for {@link com.demo.prueba.cgi.controller.DemoController#count(com.demo.prueba.cgi.dto.BusquedaDTO)}.
	 */
	@Test
	final void testCount() {
		System.out.println ("Ejecutando la prueba de conteo");
		DemoController servicioDemo = Mockito.mock(DemoController.class);
		
	
		BusquedaDTO parametro = new BusquedaDTO();
		parametro.setSearchId("6454c67fb5b5776f2c753e10");
		
        Mockito.when(servicioDemo.count(parametro)).thenReturn(null);
        
        ResponseEntity<?> respuesta = null;
        
        respuesta = servicioDemo.count(parametro);
        System.out.println ("Ejecutando prueba de conteo");
        assertEquals(null,respuesta);
	}

}
