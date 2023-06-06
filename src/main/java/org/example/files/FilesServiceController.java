package org.example.files;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FilesServiceController {

	@GetMapping("/status")
	public Status status() {
		return new Status("ok");
	}
}