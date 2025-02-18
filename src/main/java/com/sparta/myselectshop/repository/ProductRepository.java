package com.sparta.myselectshop.repository;

import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUser(User user, Pageable pageable);

    //select * from product p where p.user_id= 1;
    //select p.id, p.title as product_title, pf.product_id as product_id, pf.folder_id as folder_id
    //from product p left join product_folder pf on p.id = pf.product_id where p.user_id = 1 and pf.folder_id = 3
    // order by p.id desc
    // limit 0, 10; //개수 제한 : limit(시작 숫자, 개수)
    Page<Product> findAllByUserAndProductFolderList_FolderId(User user, Long folderId, Pageable pageable);
}
