// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sport_map.proto

package ru.elmanov.grpc.sport;

public interface PersonOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Person)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 person_id = 1;</code>
   * @return The personId.
   */
  long getPersonId();

  /**
   * <code>repeated int64 sport_ids = 2;</code>
   * @return A list containing the sportIds.
   */
  java.util.List<java.lang.Long> getSportIdsList();
  /**
   * <code>repeated int64 sport_ids = 2;</code>
   * @return The count of sportIds.
   */
  int getSportIdsCount();
  /**
   * <code>repeated int64 sport_ids = 2;</code>
   * @param index The index of the element to return.
   * @return The sportIds at the given index.
   */
  long getSportIds(int index);
}
