package com.example.projectshoes.service;

import java.io.File;

public interface IStorageStrategy {

  String[] uploadFile(File file) throws Exception;

}
