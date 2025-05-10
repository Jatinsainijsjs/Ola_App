package com.SimpleCabProject.SimpleCabProject.Security;


import com.SimpleCabProject.SimpleCabProject.Entities.Admin;
import com.SimpleCabProject.SimpleCabProject.Entities.Customer;
import com.SimpleCabProject.SimpleCabProject.Repository.AdminRepository;
import com.SimpleCabProject.SimpleCabProject.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First try to find as customer (using email)
        Optional<Customer> customerOpt = customerRepository.findByEmail(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return new User(
                    customer.getEmail(),
                    customer.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
            );
        }

        // Then try to find as admin (using username)
        Optional<Admin> adminOpt = adminRepository.findById(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return new User(
                    admin.getUsername(),
                    admin.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public UserDetails loadUserByUsernameAndType(String username, String userType) throws UsernameNotFoundException {
        if ("admin".equalsIgnoreCase(userType)) {
            Optional<Admin> adminOpt = adminRepository.findById(username);
            if (adminOpt.isPresent()) {
                Admin admin = adminOpt.get();
                return new User(
                        admin.getUsername(),
                        admin.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                );
            }
        } else if ("customer".equalsIgnoreCase(userType)) {
            Optional<Customer> customerOpt = customerRepository.findByEmail(username);
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                return new User(
                        customer.getEmail(),
                        customer.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
                );
            }
        }

        throw new UsernameNotFoundException("User not found with username: " + username + " and type: " + userType);
    }
}