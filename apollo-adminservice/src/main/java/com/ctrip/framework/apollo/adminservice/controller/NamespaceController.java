package com.ctrip.framework.apollo.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.biz.entity.Namespace;
import com.ctrip.framework.apollo.biz.service.NamespaceService;
import com.ctrip.framework.apollo.common.utils.BeanUtils;
import com.ctrip.framework.apollo.core.dto.NamespaceDTO;
import com.ctrip.framework.apollo.core.exception.NotFoundException;

@RestController
public class NamespaceController {

  @Autowired
  private NamespaceService namespaceService;

  @RequestMapping(path = "/apps/{appId}/clusters/{clusterName}/namespaces", method = RequestMethod.POST)
  public NamespaceDTO createOrUpdate(@PathVariable("appId") String appId,
                                     @PathVariable("clusterName") String clusterName, @RequestBody NamespaceDTO dto) {
    Namespace entity = BeanUtils.transfrom(Namespace.class, dto);
    Namespace managedEntity = namespaceService.findOne(appId, clusterName, entity.getNamespaceName());
    if (managedEntity != null) {
      BeanUtils.copyEntityProperties(entity, managedEntity);
      entity = namespaceService.update(managedEntity);
    } else {
      entity = namespaceService.save(entity);
    }

    dto = BeanUtils.transfrom(NamespaceDTO.class, entity);
    return dto;
  }

  @RequestMapping(path = "/apps/{appId}/clusters/{clusterName}/namespaces/{namespaceName:.+}", method = RequestMethod.DELETE)
  public void delete(@PathVariable("appId") String appId,
      @PathVariable("clusterName") String clusterName,
      @PathVariable("namespaceName") String namespaceName, @RequestParam String operator) {
    Namespace entity = namespaceService.findOne(appId, clusterName, namespaceName);
    if (entity == null) throw new NotFoundException(
        String.format("namespace not found for %s %s %s", appId, clusterName, namespaceName));
    namespaceService.delete(entity.getId(), operator);
  }

  @RequestMapping("/apps/{appId}/clusters/{clusterName}/namespaces")
  public List<NamespaceDTO> find(@PathVariable("appId") String appId,
      @PathVariable("clusterName") String clusterName) {
    List<Namespace> groups = namespaceService.findNamespaces(appId, clusterName);
    return BeanUtils.batchTransform(NamespaceDTO.class, groups);
  }

  @RequestMapping("/namespaces/{namespaceId}")
  public NamespaceDTO get(@PathVariable("namespaceId") Long namespaceId) {
    Namespace namespace = namespaceService.findOne(namespaceId);
    if (namespace == null)
      throw new NotFoundException(String.format("namespace not found for %s", namespaceId));
    return BeanUtils.transfrom(NamespaceDTO.class, namespace);
  }

  @RequestMapping("/apps/{appId}/clusters/{clusterName}/namespaces/{namespaceName:.+}")
  public NamespaceDTO get(@PathVariable("appId") String appId,
      @PathVariable("clusterName") String clusterName,
      @PathVariable("namespaceName") String namespaceName) {
    Namespace namespace = namespaceService.findOne(appId, clusterName, namespaceName);
    if (namespace == null) throw new NotFoundException(
        String.format("namespace not found for %s %s %s", appId, clusterName, namespaceName));
    return BeanUtils.transfrom(NamespaceDTO.class, namespace);
  }

}