package com.airdnb.clone.domain.stay.service;

import static com.airdnb.clone.domain.stay.entity.Stay.StayBuilder;

import com.airdnb.clone.domain.member.Member;
import com.airdnb.clone.domain.member.repository.MemberRepository;
import com.airdnb.clone.domain.stay.entity.AvailableAmenity;
import com.airdnb.clone.domain.stay.entity.RoomInformation;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.entity.Stay.Status;
import com.airdnb.clone.domain.stay.entity.Stay.Type;
import com.airdnb.clone.domain.stay.entity.StayFee;
import com.airdnb.clone.domain.stay.entity.StayImage;
import com.airdnb.clone.domain.stay.repository.AmenityRepository;
import com.airdnb.clone.domain.stay.repository.AvailableAmenityRepository;
import com.airdnb.clone.domain.stay.repository.StayRepository;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StayService {

    private final StayRepository stayRepository;
    private final MemberRepository memberRepository;
    private final AvailableAmenityRepository availableAmenityRepository;
    private final AmenityRepository amenityRepository;

    @Transactional
    public Stay create(StayBuilder stayBuilder, Long hostId, List<Long> amenityIds) {
        Stay savedStay = saveByHostId(stayBuilder, hostId);

        saveAvailableAmenities(amenityIds, savedStay);

        return savedStay;
    }

    private Stay saveByHostId(StayBuilder stayBuilder, Long hostId) {
        Member host = memberRepository.findById(hostId)
                .orElseThrow(IllegalStateException::new);

        Stay stay = stayBuilder.host(host).build();

        Stay savedStay = stayRepository.save(stay);
        return savedStay;
    }

    private void saveAvailableAmenities(List<Long> amenityIds, Stay savedStay) {
        amenityIds.stream()
                .map(amenityId -> amenityRepository.findById(amenityId).orElseThrow(IllegalStateException::new))
                .forEach(amenity -> {
                    AvailableAmenity availableAmenity = AvailableAmenity.builder() // 이용 가능 비품 생성
                            .stay(savedStay)
                            .amenity(amenity)
                            .build();

                    availableAmenity.setStay(savedStay); // 양방향 연관관계 연결

                    availableAmenityRepository.save(availableAmenity); // 이용 가능 비품 등록
                });
    }

    public Stay getStay(Long stayId) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new);
    }

    @Transactional(readOnly = true)
    public List<Stay> getStays() {
        return stayRepository.findAll();
    }

    @Transactional
    public Stay editAlias(Long stayId, String alias) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeAlias(alias);
    }

    @Transactional
    public Stay editLocation(Long stayId, String location) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeLocation(location);
    }

    @Transactional
    public Stay editCheckInOutTime(Long stayId, LocalTime checkInTime, LocalTime checkOutTime) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeCheckInOutTime(checkInTime, checkOutTime);
    }

    @Transactional
    public Stay editDescription(Long stayId, String description) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeDescription(description);
    }

    @Transactional
    public Stay editFee(Long stayId, StayFee fee) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeFee(fee);
    }

    @Transactional
    public Stay editType(Long stayId, Type type) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeType(type);
    }

    @Transactional
    public Stay editStatus(Long stayId, Status status) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeStatus(status);
    }

    @Transactional
    public List<AvailableAmenity> editAmenities(Long stayId, List<Long> amenityIds) {
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new);

        stay.getAmenities().stream()
                .filter(availableAmenity -> !amenityIds.contains(availableAmenity.getAmenity().getId()))
                .forEach(AvailableAmenity::deleteFromStay);

        return stay.getAmenities();
    }

    @Transactional
    public Stay editImages(Long stayId, List<StayImage> images) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeImages(images);
    }

    @Transactional
    public Stay editRoomInfo(Long stayId, RoomInformation roomInfo) {
        return stayRepository.findById(stayId)
                .orElseThrow(IllegalStateException::new)
                .changeRoomInfo(roomInfo);
    }

    @Transactional
    public void delete(Long stayId) {
        stayRepository.deleteById(stayId);
    }
}
