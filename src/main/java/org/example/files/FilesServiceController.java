package org.example.files;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class FilesServiceController {

	@GetMapping("/status")
	public Status status() {
		return new Status("ok");
	}

    @GetMapping("/files/{id}")
	public FileMetadata getFileMetadata(@PathVariable String id) {
        if (Integer.parseInt(id) < 0) {
            //TODO
            return null;
        }
		return new FileMetadata(1000, "newFile");
	}
}