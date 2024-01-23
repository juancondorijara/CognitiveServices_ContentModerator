package com.vg.ms_content_moderator;

import com.vg.ms_content_moderator.model.ContentModerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MsContentModeratorApplicationTests {

	@Test
	void contextLoads() {
		ContentModerator contentModerator = new ContentModerator();
		contentModerator.setId(1);
		assertNotNull(contentModerator.getId());
	}

}
