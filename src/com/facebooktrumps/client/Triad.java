package com.facebooktrumps.client;

import com.facebooktrumps.client.widgets.View;

public class Triad
{
    private Controller _parent;
    private Model _model;
    private View _view;
    private Controller _controller;
    private boolean _destroyed;

    public Triad(final Controller parent, final Model model, final View view, final Controller controller)
    {
        _parent = parent;
        _model = model;
        _view = view;
        _controller = controller;
        init();
    }

    public Triad(final Model model, final View view, final Controller controller)
    {
        this(new Controller(), model, view, controller);
    }

    public Triad(final View view, final Controller controller)
    {
        this(new NullController(), new Model(), view, controller);
    }

    public Triad(final Controller parent, final View view)
    {
        this(parent, new Model(), view, new Controller());
    }

    public Triad(final Controller parent, final View view, final Controller controller)
    {
        this(parent, new Model(), view, controller);
    }

    private void init()
    {
        setParent();
        connectTriadParts();
        initParts();
    }

    private void setParent()
    {
        if (!_parent.isNull())
            setNonRootParent();
    }

    private void setNonRootParent()
    {
        _parent.addChildController(_controller);
        putChildInMap();
    }

    private void putChildInMap()
    {
        if (_parent.getView() != null)
            _parent.getView().putChildInMap(_view.getUniqueID(), _view);
    }

    private void connectTriadParts()
    {
        _controller.setModel(_model);
        _controller.setView(_view);
        _model.setController(_controller);
        _view.setController(_controller);
    }

    protected void initParts()
    {
        _model.init();
        _controller.init();
        _view.init();
    }

    public Controller getController()
    {
        return _controller;
    }

    public View getView()
    {
        return _view;
    }

    public Model getModel()
    {
        return _model;
    }

    public void destroy()
    {
        if (_destroyed)
            return;
        Triad.destroyTriad(_view, _controller);
        _destroyed = true;
    }

    public static void destroyTriad(final View view, final Controller controller)
    {
        destroyChildTriads(controller);
        view.dispose();
        controller.dispose();
    }

    private static void destroyChildTriads(final Controller parent)
    {
        for (int i = 0; i < parent.getChildrenCount(); i++)
            destroyTriad(parent.getChildControllerAt(i).getView(),
                         parent.getChildControllerAt(i));
    }
}
