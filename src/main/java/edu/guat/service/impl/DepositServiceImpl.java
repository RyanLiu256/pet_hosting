package edu.guat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.guat.mapper.DepositMapper;
import edu.guat.po.Comment;
import edu.guat.po.Deposit;
import edu.guat.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositMapper depositMapper;

    @Override
    public PageInfo<Deposit> findAll(String username,Integer userid,Integer year,Integer month,Integer state,Integer ispay, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Deposit> deposits = depositMapper.selectAll(username,userid,year,month, state,ispay);
        PageInfo<Deposit> pageInfo = new PageInfo<>(deposits);
        return pageInfo;
    }

    @Override
    public Deposit findById(Integer id) {
        return depositMapper.selectById(id);
    }

    @Override
    public Integer updateDeposit(Deposit deposit) {
        return depositMapper.updateDeposit(deposit);
    }

    @Override
    public Integer deleteById(Integer id) {
        return depositMapper.deleteById(id);
    }

    @Override
    public List<Deposit> findMydeposit(Integer userid, String petname, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return depositMapper.selectMydeposit(userid,petname);
    }

    @Override
    public Integer addDeposit(Deposit deposit) {
        return depositMapper.addDeposit(deposit);
    }

    @Override
    public Deposit findDetail(Integer id) {
        return depositMapper.selectDetail(id);
    }

    @Override
    public Integer updatePrice(Double totalPrice, Integer id) {
        return depositMapper.updatePrice(totalPrice,id);
    }

    @Override
    public List<Deposit> findBySpeciesId(Integer speciesId) {
        return depositMapper.selectBySpeciesId(speciesId);
    }

    @Override
    public List<Deposit> findByFoodId(Integer pid) {
        return depositMapper.selectByFoodId(pid);
    }

    @Override
    public List<Deposit> findByServiceId(Integer serviceId) {
        return depositMapper.selectByServiceId(serviceId);
    }

    @Override
    public Integer updatedepositState(Integer state, Integer id) {
        return depositMapper.updateDepositState(state,id);
    }

    @Override
    public Integer changePay(Integer ispay, Integer id) {
        return depositMapper.changePay(ispay,id);
    }

    @Override
    public Integer reject(Integer state, String reason, Integer id) {
        return depositMapper.reject(state,reason,id);
    }

    @Override
    public Integer addComment(Comment comment) {
        return depositMapper.addComment(comment);
    }

    @Override
    public Integer addReply(String reply, Integer cid) {
        return depositMapper.addReply(reply,cid);
    }

    @Override
    public Integer updateCommentId(Integer commentId, Integer id) {
        return depositMapper.updateCommentId(commentId,id);
    }

    @Override
    public Comment findCommentById(Integer id) {
        return depositMapper.selectCommentById(id);
    }

    @Override
    public Comment findCommentByCid(Integer cid) {
        return depositMapper.selectCommentByCid(cid);
    }

    @Override
    public List<Deposit> findAllComment(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return depositMapper.selectAllComment();
    }

    @Override
    public Integer changeReply(String reply, Integer cid) {
        return depositMapper.updateReply(reply,cid);
    }

    @Override
    public Integer changeComment(String comment, Integer cid) {
        return depositMapper.updateComment(comment,cid);
    }

}
