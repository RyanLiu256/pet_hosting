package edu.guat.service;

import com.github.pagehelper.PageInfo;
import edu.guat.po.Comment;
import edu.guat.po.Deposit;

import java.util.List;

public interface DepositService {

    PageInfo<Deposit> findAll(String username,Integer userid,Integer year,Integer month,Integer state,Integer ispay, Integer pageNum, Integer pageSize);

    Deposit findById(Integer id);

    Integer updateDeposit(Deposit deposit);

    Integer deleteById(Integer id);

    List<Deposit> findMydeposit(Integer userid, String petname, Integer pageNum, Integer pageSize);

    Integer addDeposit(Deposit deposit);

    Deposit findDetail(Integer id);

    Integer updatePrice(Double totalPrice, Integer id);

    List<Deposit> findBySpeciesId(Integer speciesId);

    List<Deposit> findByFoodId(Integer pid);

    List<Deposit> findByServiceId(Integer serviceId);

    Integer updatedepositState(Integer state, Integer id);

    Integer changePay(Integer ispay, Integer id);

    Integer reject(Integer state, String reason, Integer id);

    Integer addComment(Comment comment);

    Integer addReply(String reply, Integer cid);

    Integer updateCommentId(Integer commentId, Integer id);

    Comment findCommentById(Integer id);

    Comment findCommentByCid(Integer cid);

    List<Deposit > findAllComment(Integer pageNum,Integer pageSize);

    Integer changeReply(String reply, Integer cid);

    Integer changeComment(String comment, Integer cid);
}
