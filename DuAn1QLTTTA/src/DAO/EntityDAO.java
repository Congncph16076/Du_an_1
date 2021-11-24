/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.util.List;
public interface EntityDAO<E,K> {
    public boolean insert(E entity,Connection conn);
    public void update(E entity,Connection conn);
    public E delete(K key,Connection conn);
    public List<E>selectAll(Connection conn);
     public E fromTableToText(K key ,Connection conn);
     public List<E> search(K key,Connection conn);
}
