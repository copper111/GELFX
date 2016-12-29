package ru.gelfx.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by aer on 30.11.16.
 */
@Service
public class GitService {


    private String localPath, remotePath;
    private Repository localRepo;
    private Git git;

    @PostConstruct
    private void init(){

    }




    public Repository openExistingLocalRepository(File directory) throws GitAPIException, IOException {

        FileRepositoryBuilder builder = new FileRepositoryBuilder();
         localRepo = builder.setGitDir(directory)
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();

        return localRepo;

    }
}