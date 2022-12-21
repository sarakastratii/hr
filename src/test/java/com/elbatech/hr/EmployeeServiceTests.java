package com.elbatech.hr;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.elbatech.hr.model.Employee;
import com.elbatech.hr.repository.EmployeeRepository;
import com.elbatech.hr.service.EmployeeService;

import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

	@InjectMocks
	private EmployeeService employeeService = new EmployeeService();
	@Mock
	private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

	

	
//	@Test
//    public void EmployeeServiceTests() {
//        // Arrange
//        when(employeeRepository.findAll()).thenReturn(List.of(new Employee(), new Employee()));
//
//        // Act & Assert
//        assertThat(employeeService.getEmployees()).hasSize(2);
//        verify(employeeRepository, times(1)).findAll();
//        verifyNoMoreInteractions(employeeRepository);
//    }
//	
	@Test
	  public void should_find_and_return_one_student() {
	        // Arrange
		  	when(employeeRepository.findByUsername("")).thenReturn(new Employee());
	       

	        // Act
	        final var actual = employeeService.getEmployeesByName("");

	        // Assert
	        assertThat(actual).usingRecursiveComparison().isEqualTo(new Employee());
	        verify(employeeRepository, times(1)).findByUsername("");
	        verifyNoMoreInteractions(employeeRepository);
	    }


}
