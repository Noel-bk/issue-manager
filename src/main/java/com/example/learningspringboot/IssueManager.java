package com.example.learningspringboot;

import org.springframework.social.github.api.GitHubIssue;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noel on 1/8/17.
 */
@Service
public class IssueManager {

    String githubToken = "061b23e772ab8f534b87c2bb4019675f80bbc587";
    String username = "noel-bk";
    String[] repos = new String[]{"issue-manager"};
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
}
