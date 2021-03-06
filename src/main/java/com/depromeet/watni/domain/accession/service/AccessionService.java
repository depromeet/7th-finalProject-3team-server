package com.depromeet.watni.domain.accession.service;

import com.depromeet.watni.domain.accession.constant.AccessionStatus;
import com.depromeet.watni.domain.accession.constant.AccessionType;
import com.depromeet.watni.domain.accession.domain.Accession;
import com.depromeet.watni.domain.accession.dto.AccessionDto;
import com.depromeet.watni.domain.accession.repository.AccessionRepository;
import com.depromeet.watni.domain.apply.domain.CodeApply;
import com.depromeet.watni.domain.apply.repository.CodeApplyRepository;
import com.depromeet.watni.domain.group.domain.Group;
import com.depromeet.watni.domain.group.service.GroupService;
import com.depromeet.watni.domain.member.domain.Member;
import com.depromeet.watni.domain.member.service.MemberService;
import com.depromeet.watni.exception.BadRequestException;
import com.depromeet.watni.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccessionService {
    private final CodeApplyRepository codeApplyRepository;
    private final AccessionRepository accessionRepository;
    private final GroupService groupService;
    private final MemberService memberService;
    public AccessionService(CodeApplyRepository codeApplyRepository, AccessionRepository accessionRepository,
                            GroupService groupService,
                            MemberService memberService) {
        this.codeApplyRepository = codeApplyRepository;
        this.accessionRepository = accessionRepository;
        this.groupService = groupService;
        this.memberService = memberService;
    }

    @Transactional
    public Accession accessGroup(AccessionDto accessionDto,Long memberId) {
        Long groupId = null;
        if(accessionDto.getCode() == null && accessionDto.getGroupId() == null){
            throw new BadRequestException("Wrong Accession Request - groupId or code is required.");
        }
        if(accessionDto.getGroupId() != null){
            groupId = accessionDto.getGroupId();
        }
        else{
            CodeApply codeApply = (CodeApply) codeApplyRepository.findOneByCode(accessionDto.getCode()).orElseThrow(()->new BadRequestException("NOT EXIST CODE"));
            groupId = codeApply.getGroup().getGroupId();
        }
        Group group = groupService.getGroup(groupId);
        Member member = memberService.selectByMemberId(memberId);
        if(accessionRepository.findOneByGroupAndMember(group,member).isPresent()){
            throw new BadRequestException("This Group is already Access");
        }
        Accession accession = Accession.builder().accessionType(AccessionType.AUTO).accessionStatus(AccessionStatus.ACCEPT).group(group).member(member).build();
        return accessionRepository.save(accession);
    }

    public void deleteAccess (Long accessId){
        accessionRepository.findById(accessId).orElseThrow(()->new NotFoundException("NOT FOUND ACCESS INFO"));
    }

    @Transactional
    public void deleteAccess (Long groupId,Long memberId){
        Group group = groupService.getGroup(groupId);
        Member member = memberService.selectByMemberId(memberId);
        Accession accession = accessionRepository.findOneByGroupAndMember(group,member).orElseThrow(()->new NotFoundException("NOT FOUND ACCESS INFO"));
        accessionRepository.delete(accession);
    }

    @Transactional
    public List<Accession> findAccessionsByMember(Long memberId){
        Member member = memberService.selectByMemberId(memberId);
        List<Accession> accessions = accessionRepository.findAllByMember(member);
        return accessions;
    }

}
