package kr.letech.study.boot.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import kr.letech.study.boot.board.dao.BoardDAO;
import kr.letech.study.boot.board.vo.BoardVO;
import kr.letech.study.boot.board.vo.PostVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardDAO boardDao;

    @Override
    public List<BoardVO> getNavItems() {
        return boardDao.getNavItems();
    }
    
    @Override
    public PageInfo<PostVO> getAllPostByBoard(BoardVO boardVO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PostVO> posts = boardDao.getAllPostByBoard(boardVO);
        return PageInfo.of(posts);
    }

	@Override
	public void modifyBoard(BoardVO boardVO, String userId) {
		boardVO.setUpdtId(userId);
		boardDao.modifyBoard(boardVO);
	}

	@Override
	public void deleteBoard(String boardId, String userId) {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardId(boardId);
		boardVO.setUpdtId(userId);
		boardDao.deleteBoard(boardVO);
	}

	@Override
	public void insertBoard(String boardNm, String userId) {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardId("b" + boardDao.getBoardSeq());
		boardVO.setBoardNm(boardNm);
		boardVO.setRgstId(userId);
		boardDao.insertBoard(boardVO);
	}
}
