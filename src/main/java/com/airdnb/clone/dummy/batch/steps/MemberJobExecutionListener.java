package com.airdnb.clone.dummy.batch.steps;

import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberJobExecutionListener implements JobExecutionListener {

    private final MemberRepository memberRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        List<Member> members = memberRepository.findAll();
        jobExecution.getExecutionContext().put("members", members);
    }
}
