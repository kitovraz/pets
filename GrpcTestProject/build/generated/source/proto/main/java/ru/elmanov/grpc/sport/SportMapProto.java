// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sport_map.proto

package ru.elmanov.grpc.sport;

public final class SportMapProto {
  private SportMapProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Person_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Person_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Sport_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Sport_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017sport_map.proto\".\n\006Person\022\021\n\tperson_id" +
      "\030\001 \001(\003\022\021\n\tsport_ids\030\002 \003(\003\"\'\n\005Sport\022\020\n\010sp" +
      "ort_id\030\001 \001(\003\022\014\n\004name\030\002 \001(\t2-\n\010SportMap\022!" +
      "\n\010getSport\022\007.Person\032\006.Sport\"\000(\0010\001B.\n\025ru." +
      "elmanov.grpc.sportB\rSportMapProtoP\001\242\002\003SP" +
      "Pb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Person_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Person_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Person_descriptor,
        new java.lang.String[] { "PersonId", "SportIds", });
    internal_static_Sport_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Sport_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Sport_descriptor,
        new java.lang.String[] { "SportId", "Name", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
