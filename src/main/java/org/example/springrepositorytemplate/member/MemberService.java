package org.example.springrepositorytemplate.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public Member create(String username, String password, String email) {
		Member member = new Member();
		member.setUsername(username);
		member.setPassword(passwordEncoder.encode(password));
		member.setEmail(email);
		member.setRole(MemberRole.USER);
		memberRepository.save(member);
		return member;
	}

	public Member findByUserName(String username) {
		return memberRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
	}
}
