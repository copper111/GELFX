package ru.gelfx.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;


/**
 * Created by aer on 07.01.17.
 */

public class GitServiceTest {

    private File localPath;
    private Repository localRepo;


    private GitService gitService;

    @Before
    public void init() throws IOException {
        localPath = new File("/home/aer/work/aicup/mytestrep");
    }


    @Test
    public void openExistingLocalRepositoryTest() throws GitAPIException, IOException {
        GitService gitService = new GitService();
        Git git = gitService.openExistingLocalRepository(localPath);
    }
}
