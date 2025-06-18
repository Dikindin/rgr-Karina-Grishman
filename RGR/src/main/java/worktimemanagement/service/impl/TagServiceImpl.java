package worktimemanagement.service.impl;

import worktimemanagement.dto.TagTaskCountDto;
import worktimemanagement.entity.Tag;
import worktimemanagement.repository.TagRepository;
import worktimemanagement.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag read(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tag> read() {
        return tagRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<TagTaskCountDto> getTaskCountsPerTag() {
        return tagRepository.countTasksByTag();
    }
}
