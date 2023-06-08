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
import java.util.HashMap;

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
		File f = storage.getFile(parsedID);
		return new FileDescriptor(parsedID, f.getMetadata().getSize(), f.getMetadata().getLabels());
	}

	//label format: "a:b,c:d"
	//need minimum name label
	@PostMapping("/files/upload")
	public FileDescriptor uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("labels") String labels) throws IOException{
		HashMap<String, String> labelsMap = new HashMap<>();
		
		Boolean nameIncluded = false;
		String[] labelsArr = labels.split(",");
		for (int i = 0; i < labelsArr.length; i++) {
			String[] oneLabel = labelsArr[i].split(":"); //"a:b" => [a,b]
			labelsMap.put(oneLabel[0], oneLabel[1]);
			if (oneLabel[0].equals("name")) {
				nameIncluded = true;
			}
		}
		if (!nameIncluded) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "must include a name label");
		}

		File newFile = storage.store(++counter, multipartFile.getBytes(), labelsMap);

		return new FileDescriptor(counter, multipartFile.getSize(), labelsMap);
	}
}