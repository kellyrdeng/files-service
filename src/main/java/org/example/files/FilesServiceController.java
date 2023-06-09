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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public FileDescriptor getFileMetadata(@PathVariable String id) {
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

	//http://localhost:8080/files?labels=name:kelly,location:portland
	//need minimum name label
	@PostMapping("/files")
	public FileDescriptor uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("labels") String labels) throws IOException{
		ArrayList<String> labelList = labelsToList(labels);
		storage.store(++counter, multipartFile.getBytes(), labelList);
		return new FileDescriptor(counter, multipartFile.getSize(), labelList);
	}

	@GetMapping("/files")
	public List<FileDescriptor> searchFiles(@RequestParam("labels") String labels) {
		ArrayList<String> labelList = labelsToList(labels);
		ArrayList<File> resultFiles = new ArrayList<>();

		List<File> firstList = storage.getFilesByLabel(labelList.get(0));
		for (File f : firstList) {
			resultFiles.add(f);
		}

		for (int i = 1; i < labelList.size(); i++) {
			resultFiles.retainAll(storage.getFilesByLabel(labelList.get(i)));
		}

		List<FileDescriptor> result = new ArrayList<FileDescriptor>();
		for(File f : resultFiles) {
			FileDescriptor fd = new FileDescriptor(f.getID(), f.getMetadata().getSize(), f.getMetadata().getLabels());
			result.add(fd);
		}
		return result;
	}

	public ArrayList<String> labelsToList(String labels) {
		ArrayList<String> labelsArrlist = new ArrayList<String>();
		
		Boolean nameIncluded = false;
		String[] labelsArr = labels.split(","); //["name:kelly", "location:portland"]
		for (int i = 0; i < labelsArr.length; i++) {
			String[] oneLabel = labelsArr[i].split(":"); //"a:b" => [a,b]
			if (oneLabel[0].equals("name")) {
				nameIncluded = true;
			}
			labelsArrlist.add(labelsArr[i]); //"name:kelly" added
		}
		if (!nameIncluded) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "must include a name label");
		}
		return labelsArrlist;
	}
}