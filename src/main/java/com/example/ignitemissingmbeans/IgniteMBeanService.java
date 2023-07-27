package com.example.ignitemissingmbeans;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class IgniteMBeanService {

    private MBeanServer mBeanServer;

    public void queryMBeans() {
        for (String groupFilter : Set.of("cache", "Kernal", "threadPools", "DataRegionMetrics")) {
            String objectQuery = format("org.apache:clsLdr=*,igniteInstanceName=*,group=%s,name=*",
                                        groupFilter);
            log.info("querying: {}", objectQuery);
            ObjectName objectNameSearch = null;
            try {
                objectNameSearch = new ObjectName(objectQuery);
            } catch (MalformedObjectNameException e) {
                throw new RuntimeException(e);
            }
            Set<ObjectInstance> mBeans = this.mBeanServer.queryMBeans(objectNameSearch, null);

            for (ObjectInstance mBean: mBeans){
                log.info("GroupName: {} Number of Attributes: {}", mBean.getObjectName().getKeyProperty("grpup"), this.getObjectAttrs( mBean, mBeanServer).size());
            }

        }
    }

    private List<MBeanAttributeInfo> getObjectAttrs(ObjectInstance oi, MBeanServer mBeanServer) {
        try {
            return asList(mBeanServer.getMBeanInfo(oi.getObjectName()).getAttributes());
        }
        catch (IntrospectionException | InstanceNotFoundException | ReflectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}