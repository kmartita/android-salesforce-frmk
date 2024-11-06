package io.omni.example.tools.metadata;

import com.sforce.soap.metadata.*;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.UserProfiles;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.omni.example.tools.metadata.MetadataUtils.MetadataTypes.PROFILE;
import static java.lang.String.format;

public class MetadataProfileUtils extends MetadataUtils {

    public Metadata[] readProfileMetadataByName(UserProfiles userProfile){
        return readMetadataRecords(PROFILE, userProfile.getName());
    }

    public <Field extends Enum<Field> & HasName> void configureFieldLevelSecurity(UserProfiles userProfile,
                                                                                  SObject sObject,
                                                                                  Field field,
                                                                                  boolean editable,
                                                                                  boolean readable) {
        Metadata[] metadata = readProfileMetadataByName(userProfile);

        if (metadata[0] != null) {
            Profile profile = (Profile) metadata[0];

            ProfileFieldLevelSecurity profileFieldSecurity = new ProfileFieldLevelSecurity();
            profileFieldSecurity.setField(format("%s.%s", sObject.getName(), field.getName()));
            profileFieldSecurity.setEditable(editable);
            profileFieldSecurity.setReadable(readable);

            List<ProfileFieldLevelSecurity> profileFieldSecurityList = new ArrayList<>();
            Collections.addAll(profileFieldSecurityList, profile.getFieldPermissions());
            profileFieldSecurityList.add(profileFieldSecurity);

            profile = new Profile();
            profile.setFullName(userProfile.getName());
            ProfileFieldLevelSecurity[] profileFieldSecurityArray = profileFieldSecurityList.toArray(new ProfileFieldLevelSecurity[0]);
            profile.setFieldPermissions(profileFieldSecurityArray);
            updateMetadata(profile);

        } else {
            throw new RuntimeException(format("No metadata for profile: %s", userProfile.getName()));
        }
    }
}
