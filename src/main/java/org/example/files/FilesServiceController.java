package org.example.files;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.example.files.storage.memory.MapStorage;


@RestController
public class FilesServiceController {
	private int counter;
	MapStorage storage = new MapStorage();

	@GetMapping("/status")
	public Status status() {
		return new Status("ok");
	}

    @GetMapping("/files/{id}")
	public FileMetadata getFileMetadata(@PathVariable String id) {
		Integer parsedID = Integer.parseInt(id);
        if (parsedID < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid id");
        }
		if (!storage.fileExists(parsedID)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource not found");
		}
		return new FileMetadata(parsedID, storage.fileSize(parsedID), "newFile");
	}

	@PostMapping("/files/upload")
	public FileMetadata uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException{
		storage.store(++counter, multipartFile.getBytes());
		System.out.println("File Size: " + multipartFile.getSize());

		return new FileMetadata(counter, multipartFile.getSize(), null);
	}
}