package com.example.learningspringboot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.github.api.GitHubIssue;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noel on 1/8/17.
 */
@Service
public class IssueManager implements InitializingBean {

    @Value("${github.token}")
    String githubToken;
    @Value("${username}")
    String username;
    @Value("${repos}")
    String[] repos;

    GitHubTemplate gitHubTemplate = new GitHubTemplate(githubToken);

    public List<Issue> findOpenIssues() {
        List<Issue> openIssues = new ArrayList<>();

        for (String repo : repos) {
            final List<GitHubIssue> issues = gitHubTemplate.repoOperations().getIssues(username, repo);

            for (GitHubIssue issue : issues) {
                if (issue.getState().equals("open")) {
                    openIssues.add(new Issue(repo, issue));
                }
            }
        }
        return openIssues;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.gitHubTemplate = new GitHubTemplate(githubToken);
    }
}
