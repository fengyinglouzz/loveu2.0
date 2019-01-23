package com.zxzyyyy.loveu.service;

import com.zxzyyyy.loveu.entity.Group;
import com.zxzyyyy.loveu.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group findById(int id) {
        return groupRepository.findById(id);
    }
}
