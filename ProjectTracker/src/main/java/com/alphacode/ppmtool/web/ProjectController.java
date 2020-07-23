package com.alphacode.ppmtool.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphacode.ppmtool.domain.Project;
import com.alphacode.ppmtool.services.MapValidationErrorService;
import com.alphacode.ppmtool.services.ProjectService;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MapValidationErrorService mapValidationErrorServices;

	// create
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, 
			BindingResult result,
			Principal principal
			) {

		ResponseEntity<?> errorMap = mapValidationErrorServices.MapValidationService(result);

		if (errorMap != null) {
			return errorMap;
		}

		Project project1 = projectService.saveOrUpdateProject(project,principal.getName());
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}

	// read one
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal ) {
		Project project = projectService.findProjectByIdentifer(projectId , principal.getName());

		return new ResponseEntity<Project>(project, HttpStatus.OK);

	}

	// read all
	@GetMapping("/all")
	public Iterable<Project> getAllProjets(Principal principal) {
		return projectService.findAllProjects(principal.getName());
	}

	// delete
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId , Principal principal) {
		projectService.deleteProjectByIdentifier(projectId,principal.getName());

		return new ResponseEntity<String>("Project with ID : '" + projectId + "' was deleted ", HttpStatus.OK);
	}

}
