package me.ruslanys.highloadcup.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import me.ruslanys.highloadcup.model.BaseModel;
import me.ruslanys.highloadcup.service.BaseService;

import java.util.Map;

/**
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 */
@Slf4j
public abstract class UpdateHandler<T extends BaseModel> extends EntityHandler<T> {

    public UpdateHandler(BaseService<T> baseService) {
        super(baseService);
    }

    @Override
    public Object handleRequest(FullHttpRequest request) throws Exception {
        int id = fetchId(request.uri());

        Map<String, String> json = readJson(request);
        T model = parseJson(json);
        model.setId(id);

        baseService.update(model);

        return "{}";
    }

}
