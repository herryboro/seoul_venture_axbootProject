package edu.axboot.domain.prac;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class PracService extends BaseService<Prac, Long> {
    private PracRepository pracRepository;

    @Inject
    public PracService(PracRepository pracRepository) {
        super(pracRepository);
        this.pracRepository = pracRepository;
    }

    public List<Prac> gets(RequestParams<Prac> requestParams) {
        return findAll();
    }
}