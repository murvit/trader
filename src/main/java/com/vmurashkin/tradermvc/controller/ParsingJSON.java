package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.model.Share;

/**
 * Created by OG_ML on 18.08.2015.
 */
public interface ParsingJSON {

    public Share parse (String json);

}
