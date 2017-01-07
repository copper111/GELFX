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


    /**
     * Открывает указанный репозиторий
     * @param directory
     * @return
     * @throws GitAPIException
     * @throws IOException
     */
    public Git openExistingLocalRepository(File directory) throws GitAPIException, IOException, RuntimeException {

        File gitDirectory = new File(directory + "/.git");
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
         localRepo = builder.setGitDir(gitDirectory)
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir()
                .setMustExist(true) // scan up the file system tree
                .build();

        git = new Git(localRepo);
        return git;

    }

    public Git getGit() {
        return git;
    }

    public void setGit(Git git) {
        this.git = git;
    }
}
